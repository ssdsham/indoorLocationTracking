package com.example.indoorlocationtracking;

public class Wifi_class {
String destination ;
String details;
long id;
String time;

Wifi_class(String dest,String det)
{
destination=dest;
details=det;
}
Wifi_class()
{

}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getDestination()
{
	return destination;
}
public void setDestination(String dest)
{
	this.destination=dest;
}

public String getDetails()
{
	return details;
}
public void setDetails(String details)
{
	this.details=details;
}
public String getTime()
{
	return time;
}
public void setTime(String time)
{
	this.time=time;
}
}
