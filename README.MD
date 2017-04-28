# The chiwoo-mybatis module supports the following functional features.
  - It has supported PRS(Partial Range Scan) query.
  - It has supported DBMS vendor specific feature through dialect.
  - It has supported JDBC DataTypeHandler.  
  - It has supported GenericDao through BaseMapper and ExtMapper.
  - It has supported common collection map through ResultMap.
  - It has handled Query Logger with binded parameters.
  - Automatically fetches the total record count based on the condition.
  - Automatically handles column sort criteria based on conditions.

Note that, To install into the local maven repository, run the following command:
~~~
mvn clean source:jar javadoc:jar install
~~~