package polymorphism;

import org.springframework.stereotype.Component;


@Component("tMaxSpeaker")
public class TmaxSpeaker implements Speaker {
	public TmaxSpeaker() {
		System.out.println("===> TmaxSpeaker 按眉 积己");
	}
	
	@Override
	public void volumeUp() {
		System.out.println("TmaxSpeaker --- 家府 棵赴促");
	}
	
	@Override
	public void volumeDown() {
		System.out.println("TmaxSpeaker --- 家府 郴赴促.");
	}
}
