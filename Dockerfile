FROM gradle:7.1.1-jdk11

ADD . /apigoldfile/
WORKDIR /apigoldfile/

CMD ["sleep", "inf"]