package txt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.szzf;
import jdbc.sqlinsert;

public class TxtRead {
    public static void main(String[] args) throws IOException {
    	String[]	szquyu={"luohu","futian","nanshan","yantian","baoan","longgang","longhuaxinqu"};
    	String[][]	quyu={{"buxin","chunfenglu","dongmen","diwang","honghu","huangbeiling",
			"luohukouan","liantang","sungang","wanxiangcheng","xinxiu","yinhu"},
			{"baihua","bijiashan","bagualing","chegongmiao","futianzhongxin",
    			"futianbaoshuiqu","huaqiangnan","huaqiangbei","huanggang","huaqiaocheng1",
    			"jingtian","lianhua","meilin","shangxiasha","shangbu","shixia","xiangmihu",
    			"xinzhou1","yuanling","zhuzilin"},
    			{"baishizhou","daxuecheng3","houhai","hongshuwan","kejiyuan","nantou",
        			"nanshanzhongxin","qianhai","shekou","shenzhenwan","xili1"},
        			{"meisha","shatoujiao","yantiangang"},
        			{"baoanzhongxin","fuyong","gongming","songgang","shajing","shiyan","taoyuanju",
            			"xinan","xicheng1","xixiang"},
            			{"bantian","buji","henggang","longgangzhongxin","nanlian","pingshan",
                			"pingdi","pinghu"},
                			{"dalang","guanlan","longhuazhongxin","minzhi","qinghu","shenzhenbeizhan"}};

        for(int i=0;i<szquyu.length;i++){
        	for(int j=0;j<quyu[i].length;j++){
        		FileInputStream  read = new FileInputStream("D:/github/github project/python_pa/"+szquyu[i]+"/"+quyu[i][j]+".txt");//考虑到编码格式
        		InputStreamReader isr = new InputStreamReader(read,"UTF-8"); //指定以UTF-8编码读
        		@SuppressWarnings("resource")
        		BufferedReader bufferedReader = new BufferedReader(isr);
        		//可以换成工程目录下的其他文本文件
        		String s = bufferedReader.readLine();
        		do{
        			szzf zfinfo = new szzf();
        			zfinfo.setArea(szquyu[i]);
        			zfinfo.setRegion(quyu[i][j]);				
        			splitt(s, zfinfo);
        			s = bufferedReader.readLine();
        		}while(s!=null);
        	}
        }
    }
     
    public static String[] splitt(String str, szzf zfinfo){
    	sqlinsert sql = new sqlinsert();
    	
        String strr = str.trim();
        String[] abc = strr.split("   ");
        //community
        String str1 = abc[0];
        System.out.print(str1);
        zfinfo.setCommunity(str1);
        //room and hall
        String str2 = abc[1];
        //String strr2 = str2.trim();
        //String[] abc2 = strr2.split("");
        Matcher m = findint(str2);
        int room = 0;
        if(m.find()){
        	room = Integer.parseInt(m.group());
        	System.out.print(m.group());
        	zfinfo.setRoom(room);
        }
        
        if(m.find()){
        	System.out.print(m.group());
        	zfinfo.setHall(Integer.parseInt(m.group()));
        }
        //meter
        String str3 = abc[2];
        m = findint(str3);
        int meter = 0;
        if(m.find()){
        	meter = Integer.parseInt(m.group());
        	System.out.print(m.group());
        	zfinfo.setMeter(meter);
        }
        if(room == 0)
        	room = meter/25;
        if(room == 0)
        	room = 1;
        //price
        String str4 = abc[3];
        m = findint(str4);
        int price = 0;
        if(m.find()){
        	price = Integer.parseInt(m.group());
        	System.out.println(m.group());
        	zfinfo.setPrice(price);
        }
        zfinfo.setPricePRoom(price/room);
        if(meter == 0)
        	return null;
        zfinfo.setPricePMeter(price/meter);
        sql.insert(zfinfo);
        return abc;
    }
    
    //查找字符中数字
    public static Matcher findint(String str){
        Pattern p = Pattern.compile("[0-9\\.]+");
        Matcher m = p.matcher(str);
        return m;
    }
     
}
