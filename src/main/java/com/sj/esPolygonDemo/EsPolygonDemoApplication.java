package com.sj.esPolygonDemo;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.common.geo.builders.CoordinatesBuilder;
import org.elasticsearch.common.geo.builders.PolygonBuilder;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class EsPolygonDemoApplication implements CommandLineRunner {

	@Autowired
	private EsProcess esProcess;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(EsPolygonDemoApplication.class);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		Location location = new Location();
		location.setCity("LA");
		PolygonBuilder polygonBuilder = new PolygonBuilder(new CoordinatesBuilder()
				.coordinate(1,1) .coordinate(4,1) .
				coordinate(4,3) .coordinate(1,3) .coordinate(1,1));
		location.setPolygon(polygonBuilder);
		location.setTime(new Date());
		String json = JSON.toJSONString(location);
		esProcess.createIndex("location", "city", json);

		//solve exception
//		Map<String, Object> map = new HashMap<>(2);
//		map.put("polygon", polygonBuilder);
//		map.put("time", new Date());
//		XContentBuilder xContentBuilder = esProcess.createDoc(map);
//		esProcess.createIndex("location", "city", "1", xContentBuilder);
	}
}
