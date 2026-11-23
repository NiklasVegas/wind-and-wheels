package de.example.windandwheels.entity;

import jakarta.persistence.*;


    @Entity
    @Table(name = "locations")
    public class LocationEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private double latitude;
        private double longitude;

        public double getLongitude() {
            return longitude;
        }

        public Long getId() {
            return id;
        }

        public double getLatitude() {
            return latitude;
        }

        public String getName(){
            return name;
        }



        protected LocationEntity() {
        }
        public LocationEntity(long id, String name, double latitude, double longitude){
            this.id = id;
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
        }

    }

