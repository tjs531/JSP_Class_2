package polymorphism;

public class SamsungTV implements Tv {
	
	private Speaker speaker;										//is관계 (tv=samsung tv)는 implements , 포함관계(speaker는 tv에 포함)는 변수로.
	
	public SamsungTV() {
		System.out.println("SamsungTV 객체화!!");
	}
	
	public SamsungTV(Speaker speaker) {
		this.speaker = speaker;
	}
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	@Override
	public void powerOn() {
		System.out.println("SamsungTV ---- 전원켠다");
	}
	@Override
	public void powerOff() {
		System.out.println("SamsungTV ---- 전원끈다");
	}
	@Override
	public void volumeUp() {
		speaker.volumeUp();
	}
	@Override
	public void volumeDown() {
		speaker.volumeDown();
	}

}
