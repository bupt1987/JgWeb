package model;
import java.net.URI;
import java.nio.channels.NotYetConnectedException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

public class MyWebSocketClient extends WebSocketClient {
	
	private boolean received = false;
	private String message;

	public MyWebSocketClient( URI serverUri , Draft draft ) {
		super( serverUri, draft );
	}

	public MyWebSocketClient( URI serverURI ) {
		super( serverURI );
	}
	
	public String getMessage(){
		return message;
	}
	
	@Override
	public void send(String text) throws NotYetConnectedException {
		synchronized (this) {
			super.send(text);
			while (!received) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			received = false;
		}
	}

	@Override
	public void onOpen( ServerHandshake handshakedata ) {
//		System.out.println( "opened connection" );
		// if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
	}

	@Override
	public void onMessage( String message ) {
		this.message = message;
		synchronized (this) {
			received = true;
			this.notify();
		}
	}

	public void onFragment( Framedata fragment ) {
		System.out.println( "received fragment: " + new String( fragment.getPayloadData().array() ) );
		synchronized (this) {
			received = true;
			this.notify();
		}
	}

	@Override
	public void onClose( int code, String reason, boolean remote ) {
		// The codecodes are documented in class org.java_websocket.framing.CloseFrame
//		System.out.println( "Connection closed by " + ( remote ? "remote peer" : "us" ) );
		synchronized (this) {
			received = true;
			this.notify();
		}
	}

	@Override
	public void onError( Exception ex ) {
		ex.printStackTrace();
		// if the error is fatal then onClose will be called additionally
		synchronized (this) {
			received = true;
			this.notify();
		}
	}

}