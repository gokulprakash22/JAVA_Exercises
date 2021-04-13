package spring;


import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class ExportImpl extends DelegatingIntroductionInterceptor implements Export {
	@Override
	public void doExport() {
		System.out.println("Export method called.......");

	}
	
}
