

package com.mycompany.flinkcommerce;

import Deserializer.JSONValueDeserializationSchema;
import com.mycompany.Dto.Transaction;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStream;


public class DataStreamJob {

	public static void main(String[] args) throws Exception {
		// Sets up the execution environment, which is the main entry point
		// to building Flink applications.
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

                String topic = "financial_transactions";
                KafkaSource<Transaction> source = KafkaSource.<Transaction>builder()
                        .setBootstrapServers("localhost:9092")
                        .setTopics(topic)
                        .setGroupId("FlinkCommerce")
                        .setStartingOffsets(OffsetsInitializer.earliest())
                        .setValueOnlyDeserializer(new JSONValueDeserializationSchema())
                        .build();
                
                DataStream<Transaction> transactionStream = env.fromSource(source, WatermarkStrategy.noWatermarks(), "Kafka source");
                
                transactionStream.print();
                
		env.execute("Flink Java API Skeleton");
	}
}
