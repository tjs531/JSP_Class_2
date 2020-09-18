package polymorphism;

import org.springframework.stereotype.Component;


@Component("tMaxSpeaker")
public class TmaxSpeaker implements Speaker {
	public TmaxSpeaker() {
		System.out.println("===> TmaxSpeaker ��ü ����");
	}
	
	@Override
	public void volumeUp() {
		System.out.println("TmaxSpeaker --- �Ҹ� �ø���");
	}
	
	@Override
	public void volumeDown() {
		System.out.println("TmaxSpeaker --- �Ҹ� ������.");
	}
}
