# Agendador de Email

1. WildFly 20 
2. JakartaEE
3. VueJS
4. EJB
5. CDI
6. JNDI



CD C:\Users\XXXXXXX\Documents\wildfly20\wildfly-20.0.1.Final\bin
bin > jboss-cli.bat --connect


# configurando o mySql no wildFly
1. module add --name=com.mysql --resources=/path/to/mysql-connector-java-8.0.15.jar --dependencies=javax.api,javax.transaction.api
2. /subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.cj.jdbc.MysqlXADataSource)
3. data-source add --name=AgendamentoDS --jndi-name=java:jboss/datasources/AgendamentoDS --driver-name=mysql  --connection-url=jdbc:mysql://localhost:3306/agendamentobd --user-name=SEU-USUARIO --password=SUA-SENHA --min-pool-size=10 --max-pool-size=20

# Configurando o Email no wildFly


1. /subsystem=mail/mail-session=agendamentoMailSession:add(jndi-name=java:jboss/mail/AgendamentoMailSession)
2. /socket-binding-group=standard-sockets/remote-destination-outbound-socket-binding=my-smtp-binding:add(host=smtp.mailtrap.io, port=2525)
3. /subsystem=mail/mail-session=agendamentoMailSession/server=smtp:add(outbound-socket-binding-ref= my-smtp-binding, username=bc82647d48b758, password=6320632ea13bd1, tls=true)
