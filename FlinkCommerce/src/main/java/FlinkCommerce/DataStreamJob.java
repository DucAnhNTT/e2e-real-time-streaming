/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package FlinkCommerce;

import Deserializer.JSONValueDeserializationSchema;
import Dto.SalesPerCategory;
import Dto.SalesPerDay;
import Dto.SalesPerMonth;
import Dto.Transaction;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.connector.sink.Sink;
import org.apache.flink.connector.elasticsearch.sink.Elasticsearch7SinkBuilder;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcStatementBuilder;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.elasticsearch7.shaded.org.apache.http.HttpHost;
import org.apache.flink.elasticsearch7.shaded.org.elasticsearch.action.index.IndexRequest;
import org.apache.flink.elasticsearch7.shaded.org.elasticsearch.client.Requests;
import org.apache.flink.elasticsearch7.shaded.org.elasticsearch.common.xcontent.XContentType;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.connector.jdbc.JdbcSink;

import java.sql.Date;

import static utils.JsonUtil.convertTransactionToJson;

public class DataStreamJob {
    private static final String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
    private static final String username = "postgres";
    private static final String password = "postgres";

    public static void main(String[] args) throws Exception {
        // Sets up the execution environment, which is the main entry point
        // to building Flink applications.
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        String topic = "financial_transactions";

        KafkaSource<Transaction> source = KafkaSource.<Transaction>builder()
                .setBootstrapServers("localhost:9092")
                .setTopics(topic)
                .setGroupId("flink-group")
                .setStartingOffsets(OffsetsInitializer.earliest())
                .setValueOnlyDeserializer(new JSONValueDeserializationSchema())
                .build();

        DataStream<Transaction> transactionStream = env.fromSource(source, WatermarkStrategy.noWatermarks(), "Kafka source");

        transactionStream.print();

        JdbcExecutionOptions execOptions = new JdbcExecutionOptions.Builder()
                .withBatchSize(1000)
                .withBatchIntervalMs(200)
                .withMaxRetries(5)
                .build();

        JdbcConnectionOptions connOptions = new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                .withUrl(jdbcUrl)
                .withDriverName("org.postgresql.Driver")
                .withUsername(username)
                .withPassword(password)
                .build();


        //create transactions table
        transactionStream.addSink(JdbcSink.sink(
                "CREATE TABLE IF NOT EXISTS transactions (" +
                        "transaction_id VARCHAR(255) PRIMARY KEY, " +
                        "product_id VARCHAR(255), " +
                        "product_name VARCHAR(255), " +
                        "product_category VARCHAR(255), " +
                        "product_price DOUBLE PRECISION, " +
                        "product_quantity INTEGER, " +
                        "product_brand VARCHAR(255), " +
                        "total_amount DOUBLE PRECISION, " +
                        "currency VARCHAR(255), " +
                        "customer_id VARCHAR(255), " +
                        "transaction_date TIMESTAMP, " +
                        "payment_method VARCHAR(255) " +
                        ")",
                (JdbcStatementBuilder<Transaction>) (preparedStatement, transaction) -> {

                },
                execOptions,
                connOptions
        )).name("Create Transactions Table Sink");

        transactionStream.addSink(JdbcSink.sink(
                "INSERT INTO transactions(transaction_id, product_id, product_name, product_category, product_price, " +
                        "product_quantity, product_brand, total_amount, currency, customer_id, transaction_date, payment_method) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                        "ON CONFLICT (transaction_id) DO UPDATE SET " +
                        "product_id = EXCLUDED.product_id, " +
                        "product_name  = EXCLUDED.product_name, " +
                        "product_category  = EXCLUDED.product_category, " +
                        "product_price = EXCLUDED.product_price, " +
                        "product_quantity = EXCLUDED.product_quantity, " +
                        "product_brand = EXCLUDED.product_brand, " +
                        "total_amount  = EXCLUDED.total_amount, " +
                        "currency = EXCLUDED.currency, " +
                        "customer_id  = EXCLUDED.customer_id, " +
                        "transaction_date = EXCLUDED.transaction_date, " +
                        "payment_method = EXCLUDED.payment_method " +
                        "WHERE transactions.transaction_id = EXCLUDED.transaction_id",
                (JdbcStatementBuilder<Transaction>) (preparedStatement, transaction) -> {
                    preparedStatement.setString(1, transaction.getTransactionId());
                    preparedStatement.setString(2, transaction.getProductId());
                    preparedStatement.setString(3, transaction.getProductName());
                    preparedStatement.setString(4, transaction.getProductCategory());
                    preparedStatement.setDouble(5, transaction.getProductPrice());
                    preparedStatement.setInt(6, transaction.getProductQuantity());
                    preparedStatement.setString(7, transaction.getProductBrand());
                    preparedStatement.setDouble(8, transaction.getTotalAmount());
                    preparedStatement.setString(9, transaction.getCurrency());
                    preparedStatement.setString(10, transaction.getCustomerId());
                    preparedStatement.setTimestamp(11, transaction.getTransactionDate());
                    preparedStatement.setString(12, transaction.getPaymentMethod());
                },
                execOptions,
                connOptions
        )).name("Insert into transactions table sink");

        // Execute program, beginning computation.
        env.execute("Flink Ecommerce Realtime Streaming");
    }
}
