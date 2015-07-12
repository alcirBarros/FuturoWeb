# security
Projeto java que implementa security para acesso as paginas 

--
Configure arquivo "jaas.config"

apache-tomcat/conf/jaas.config

```xhtml
  SaluteLogin {
      br.com.selfSystem.security.FuturoWebLoginModule required debug=true;
  };
  ```
  
##Requisitos
- Java
- Maven
- Apache-Tomcat
