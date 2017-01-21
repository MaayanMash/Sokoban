package model.data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import commons.Level2D;
import commons.level;

public class MyTextLevelSaver implements levelSaver {

	@Override
	public void seveLevel(level level, OutputStream out) throws IOException {
		Level2D l2d = (Level2D)level;
		BufferedWriter outTxt = new BufferedWriter(new OutputStreamWriter(out));
		for(int i=0;i<l2d.getSizeRow();i++){
			for(int j=0;j<l2d.getSizeCol();j++){
				if(l2d.getMoveables()[i][j]!=null){
					if(l2d.getBackG()[i][j].getC()== 'o'||l2d.getBackG()[i][j].getC()== 'O')
					{
						if(l2d.getMoveables()[i][j].getC() == 'A')//player on target
							outTxt.write("a");
						else
							outTxt.write("$");//Box on Target
					}
					else
						outTxt.write(l2d.getMoveables()[i][j].getC());
				}
				else{
					outTxt.write(l2d.getBackG()[i][j].getC());
				}
			}
			outTxt.newLine();
		}
		outTxt.close();
		System.out.println("txt file saved!");
		return;	

	}

}
