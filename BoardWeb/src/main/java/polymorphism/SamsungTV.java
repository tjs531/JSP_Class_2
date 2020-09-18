package polymorphism;

public class SamsungTV implements Tv {
	
	private Speaker speaker;										//is���� (tv=samsung tv)�� implements , ���԰���(speaker�� tv�� ����)�� ������.
	
	public SamsungTV() {
		System.out.println("SamsungTV ��üȭ!!");
	}
	
	public SamsungTV(Speaker speaker) {
		this.speaker = speaker;
	}
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	@Override
	public void powerOn() {
		System.out.println("SamsungTV ---- �����Ҵ�");
	}
	@Override
	public void powerOff() {
		System.out.println("SamsungTV ---- ��������");
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
