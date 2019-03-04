# PC Room Program(PC방 프로그램) 

*주요 Class 목록

*ClientController
- ClientView로부터 Data를받아 ServerController에 보내고 ServerController로부터 받은
Data를 ClientView에 보내줍니다. 또한 필요시 ClientModel의 Data를 Setting/Update합니다.

*ClientModel
- Client 가 사용하고 있는 해당 PC의 Data(Pc번호,사용시간,남은시간,선불/후불 등)의 Data를 저장하고
필요시 ClientController에서 사용합니다.

*ClientVieW
- Console환경으로 Client가 사용하는 View입니다. 이 클래스를 실행하여 Client는 Pc의 사용여부, 사용시간,
선불/후불등을 결정해 이용하게 됩니다.

*ServerController
- Tcp Client들을 관리하며 각 Client별 Data를 Setting/Update합니다.
또한 Setting/Update한 Data를 각 ClientController에게 송신/수신을 하는 역할을 하고, 필요시 ServerView에게
Data를 보내고 받기도 합니다. TcpServerEventHandler의 Impliment가 이루어집니다.

*ServerModel
- 각 Tcp Client들의 전체 Data들을 총 저장하는 곳입니다. ServerController에게 사용할 Data들을 제공하는 역할을 
합니다.

*ServerView
- Swing 으로 구현한 GUI 환경의 Server 즉 PC방 운영자가 사용하는 View입니다. 각종 PC의 관리를 GUI환경에서 실행하며 
해당 작업의 내용은 ServerController에게 Data를 주고받으며 이루어집니다.

*TcpClient
- Tcp통신의 Client부분의 구현이며  Server/Client간의 Exception처리, Connect, send , close등의 기능을 구현합니다.
Thread로 스트림으로 Server와 메세지를 주고받는 실제구현이 포함되어있습니다.

*TcpServer
- TcpClientEventHandler의 Impliment가 이루어집니다.Server를 열고 Accept하며 Close하는 등 서버를 열고 닫는 역할을 하는
실제 Tcp Server의 구현입니다.

*TcpClientEventHandler
- interface로써 TcpClientEventHandler의 틀을 정의합니다.

*TcpServerEventHandler
- interface로써 TcpServerEventHandler의 틀을 정의합니다.





