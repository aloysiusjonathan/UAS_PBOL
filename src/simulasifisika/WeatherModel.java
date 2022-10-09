/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulasifisika;

public class WeatherModel {
    private String weather_id, weather_name;
    private int wind_speed, wind_direction;
    private float weather_effect;

    public String getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(String weather_id) {
        this.weather_id = weather_id;
    }

    public String getWeather_name() {
        return weather_name;
    }

    public void setWeather_name(String weather_name) {
        this.weather_name = weather_name;
    }

    public int getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(int wind_speed) {
        this.wind_speed = wind_speed;
    }

    public int getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(int wind_direction) {
        this.wind_direction = wind_direction;
    }

    public float getWeather_effect() {
        return weather_effect;
    }

    public void setWeather_effect(float weather_effect) {
        this.weather_effect = weather_effect;
    }
}
