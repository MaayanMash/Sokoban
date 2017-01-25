package controller.generic;




public interface iCommand {
	public void execute() throws Exception;
	public void setParams(String params);
	
}
