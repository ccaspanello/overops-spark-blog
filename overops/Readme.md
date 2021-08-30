# OverOps

## Installation
The easiest way to get up and running with OverOps on your machine is to leverage Docker.  Provided is a `docker-compose.yml` file which will setup the following:
* Continuous Reliablilty Dashboard (Port: 8080)
  * Default Login: default.user@company.com / 123456
* Collector (Port: 6060)

## Starting OverOps
In this directory run `docker-compose up -d`

## Stopping OverOps
In this directory run `docker-compose stop`

## Cleaning up OverOps
Run the following:
* In this directory run `docker-compose rm`
* Run `docker rmi overops/collector`
* Run `docker rmi overops/server`
