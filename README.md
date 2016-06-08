# Web Service contendo as requisições: GET, POST, PUT e DELETE.

Depois de realizar o download do projeto é necessário realizar as seguintes etapas:
  1. Configurar um schema no banco de dados MySQL com o nome flatws
    Ex: Create database flatws;
  2. Adicionar o Junit ao build path do projeto.
  3. Configurar o arquivo log4j.xml, localizado na pasta resource. Alterando o local onde os arquivos de logs serão gravados.
    Alterar todos os campos onde estiver a anotação <!-- Configure o local para Salvar o Log -->, conforme exemplo abaixo: 

		<RollingFile name="ROLLING"
			fileName="<!-- Configure o local para Salvar o Log -->/logsroll.log"
			          <!-- Ex. c:/users/alexcoimbra12/log/logsroll.log -->
			filePattern="<!-- Configure o local para Salvar o Log -->/logsroll-%d{MM-dd-yyyy}-%i.log">
			          <!-- Ex. c:/users/alexcoimbra12/log/logsroll.log -->
			<PatternLayout pattern="[%d] [%-5level] [%thread] %class.%method\(\) \(%F:%line\)
					%n%msg%n----------------------------------------------------------------------------------------------------------------%n" />
			<Policies>
				<TimeBasedTriggeringPolicy />
				<SizeBasedTriggeringPolicy size="0.001 MB" />
			</Policies>
			<DefaultRolloverStrategy max="10" />
		</RollingFile>
		
		
Realizado as configurações acima, é só rodar o projeto e testar as requisições.

O Web Service ainda está em desenvolvimento, assim como o front-end, que pode ser encontrado em:  https://github.com/alexcoimbra12/ControleDeFlatFrontEnd
