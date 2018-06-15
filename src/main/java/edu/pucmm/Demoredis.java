package edu.pucmm;

import java.util.function.Predicate;
import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class Demoredis {

	public static void main(String[] args) {
		// Configuro mi redis
		Config config = new Config();
		// Hago mi conexion
		config.useSingleServer().setAddress("redis://localhost:6379");
		RedissonClient redisson = Redisson.create(config);

		// Hago la lista
		RList<Demo> demos = redisson.getList("data");
		if (demos.isEmpty()) {
			

			demos.add(new Demo("Albert", "Manzana"));
			demos.add(new Demo("Paul", "Pera"));
			demos.add(new Demo("Emma", "Piña"));
			demos.add(new Demo("Pablo", "Chinola"));
			demos.add(new Demo("Eva", "Tamarindo"));
			demos.add(new Demo("Laura", "Jagua"));
			demos.add(new Demo("Jean", "Guayaba"));
			demos.add(new Demo("Misael", "Limon"));
			demos.add(new Demo("Klyf", "Fresa"));
			demos.add(new Demo("Juan", "Melon"));
			demos.add(new Demo("Aluis", "Cereza"));
			demos.add(new Demo("Richard", "Jobo"));
			demos.add(new Demo("Pedro", "Pitajaya"));
			demos.add(new Demo("Carlos", "Aguacate"));
			demos.add(new Demo("Richard", "Naranja"));

		}

		// Elimino un item que tenga la letra M

		demos.removeIf(new Predicate<Demo>() {

			@Override
			public boolean test(Demo demo) {
				return demo.getName().equals("M");
			}
		});

		// Recorrer mi lista y hago un toString por cada elemento

		for (Demo demo : demos) {
			System.out.println(demo.toString());
		}

		// Apago REDIS
		redisson.shutdown();
	}

	

	public static class Demo {

		private String name;
		private String fruta;

		public Demo() {
		}

		public Demo(String name, String fruta) {
			this.name = name;
			this.fruta = fruta;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getfruta() {
			return fruta;
		}

		public void setFruta(String fruta) {
			this.fruta = fruta;
		}

		@Override
		public String toString() {
			return name + " -- " + fruta.toString();
		}

	}

}
