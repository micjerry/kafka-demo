1. mvn package jars
  
2. install apps with install_apps.sh  
the install path like this
producer/  
├── config  
│   └── application.yml  
├── install_app.sh  
├── kama-producer-file.conf  
└── kama-producer-file.jar  

./install_apps.h -s kamaproducer
./install_apps.h -s kamaconsumer
  
the apps was installed in /opt/kafkademo  
  
3. run apps  
execute the jar directly
or 
systemctl start kamaproducer.service
systemctl start kamaconsumer.service
  
4. test apps  
send file to kafka  
curl --header "Content-Type: application/json" --request POST --data '{"filename":"/opt/hansf/kama/test.txt"}' http://localhost:9001//static/sendfile  
  
consumer will print the content of file  

