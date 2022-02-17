package _2designPattern._5adapter._2diffcultEx;

public class WaveForm {
	private static long counter;
	private final long id = counter++;
	
	public String toString() {
		return "Waveform " + id;
	}
}


class Filter  {
	public String name() {
		return this.getClass().getSimpleName();
	}
	public WaveForm proccess(WaveForm input) {
		return input;
	}
}

class LowPass extends Filter{
	double cutoff;

	public LowPass(double cutoff) {
		super();
		this.cutoff = cutoff;
	}
	
	@Override
	public WaveForm proccess(WaveForm input) {
		return input;  // 假装处理
	}
}

class HighPass extends Filter{
	double cutoff;

	public HighPass(double cutoff) {
		super();
		this.cutoff = cutoff;
	}
	
	@Override
	public WaveForm proccess(WaveForm input) {
		return input;
	}
}

class BandPass extends Filter{
	double lowCutoff;
	double highCutoff;

	public BandPass(double lowCutoff, double highCutoff) {
		super();
		this.lowCutoff = lowCutoff;
		this.highCutoff = highCutoff;
	}


	@Override
	public WaveForm proccess(WaveForm input) {
		return input;  // ���⴦��
	}
}





