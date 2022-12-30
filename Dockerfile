FROM ubuntu:16.04

RUN apt-get update
RUN apt-get install default-jre
RUN apt-get install default-jdk
#
#ENV JAVA_HOME "/usr/lib/jvm/java-8-oracle"
#
## Install Curl
#RUN apt-get update -y && apt-get install -y curl
#
## Install Docker
##RUN apt-get update -y \
##	&& apt-get install -y apt-transport-https ca-certificates \
##	&& apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D \
##	&& echo "deb https://apt.dockerproject.org/repo ubuntu-xenial main" | tee -a /etc/apt/sources.list.d/docker.list \
##	&& apt-get update -y \
##	&& apt-get purge lxc-docker \
##	&& apt-cache policy docker-engine \
##	&& apt-get update -y \
##	&& apt-get install docker-engine -y
#
## Install java
#RUN \
#	apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886 \
#	&& apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 642AC823 \
#	&& echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee /etc/apt/sources.list.d/webupd8team-java.list \
#	&& echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list \
#	&& echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | debconf-set-selections \
#	&& apt-get update -y \
#	&& apt-get install -y oracle-java8-installer
#
## Install NodeJS
#RUN \
#	curl -sL https://deb.nodesource.com/setup_6.x -o nodesource_setup.sh \
#	&& apt-get update -y \
#	&& apt-get install -y nodejs
#
## Install SBT
#RUN \
#	echo "deb http://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list \
#	&& apt-get update -y \
#	&& apt-get install -y sbt \
#  && sbt sbtVersion
#
#RUN sbt run