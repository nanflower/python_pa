# -*- coding: utf-8 -*-

import re
import urllib2  
import sqlite3
import random
import threading
from bs4 import BeautifulSoup

import sys
reload(sys)
sys.setdefaultencoding("utf-8")

szquyu=["luohu","futian","nanshan","yantian","baoan","longgang","longhuaxinqu"]

lhquyu=["buxin","chunfenglu","dongmen","diwang","honghu","huangbeiling",
"luohukouan","liantang","sungang","wanxiangcheng","xinxiu","yinhu"]

ftquyu=["baihua","bijiashan","bagualing","chegongmiao","futianzhongxin",
"futianbaoshuiqu","huaqiangnan","huaqiangbei","huanggang","huaqiaocheng1",
"jingtian","lianhua","meilin","shangxiasha","shangbu","shixia","xiangmihu",
"xinzhou","yuanling","zhuzilin"]

nsquyu=["baishizhou","daxuecheng3","houhai","hongshuwan","kexueyuan","nantou",
"nanshanzhongxin","qianhai","shetou","shenzhenwan","xili"]

ytquyu=["meisha","shatoujiao","yantiangang"]

baquyu=["baoanzhongxin","fuyong","gongming","songgang","shajing","shiyan","taoyuanju",
"xinan","xicheng","xixiang"]

lgquyu=["bantian","buji","henggang","longgangzhongxin","nanlian","pingshan",
"pingdi","pinghu"]

lhxqquyu=["dalang","guanlan","longhuazhongxin","minzhi","qinghu","shenzhenbeizhan"]

bc=["xicheng1","xixiang"]


def do_diqu_spider(area,region,number):
    if number == 1:
        url=u"http://sz.lianjia.com/zufang/"+region+"/"
    else:
        url=u"http://sz.lianjia.com/zufang/"+region+"/pg"+str(number)+"/"
    try:
        req = urllib2.Request(url)#headers=hds[random.randint(0,len(hds)-1)])
        source_code = urllib2.urlopen(req,timeout=5).read()
        plain_text=unicode(source_code)#,errors='ignore')   
        soup = BeautifulSoup(plain_text, "lxml")
    except (urllib2.HTTPError, urllib2.URLError), e:
        print e
        return
    except Exception,e:
        print e
        return
    

    xiaoqu_list=soup.findAll('div',{'class':'info-panel'})
    for xq in xiaoqu_list:
        info_dict={}
        href=xq.find('a')
        if not href:
            continue
        #    info_dict.update({u'户型':content[1]})
        #    info_dict.update({u'面积':content[2]})
        locate = xq.find('span', 'region')
        #print locate.get_text()
        zone = xq.find('span','zone')
        #print zone.get_text()
        meter = xq.find('span','meters')
        #print meter.get_text()
        price = xq.find('div', 'price')
        #print price.get_text()
        pathname =  area + '/' + region + '.txt';
        f = open(pathname,'a')
        f.write(locate.get_text()+" "+zone.get_text()+" "+meter.get_text()+" "+price.get_text()+"\n")

    #threads=[]
    #for i in range(total_pages):
    #    url_page=u"http://bj.lianjia.com/xiaoqu/pg%drs%s/" % (i+1,region)
    #    t=threading.Thread(target=xiaoqu_spider,args=(db_xq,url_page))
    #    threads.append(t)
    #for t in threads:
    #    t.start()
    #for t in threads:
    #    t.join()

def pg_spider(area,region):
    url=u"http://sz.lianjia.com/zufang/"+region+"/"

    try:
        req = urllib2.Request(url)#headers=hds[random.randint(0,len(hds)-1)])
        source_code = urllib2.urlopen(req,timeout=5).read()
        plain_text=unicode(source_code)#,errors='ignore')   
        soup = BeautifulSoup(plain_text, "lxml")
    except (urllib2.HTTPError, urllib2.URLError), e:
        print e
        return
    except Exception,e:
        print e
        return

    d="d="+soup.find('div',{'class':'page-box house-lst-page-box'}).get('page-data')
    exec(d)
    number=d['totalPage']


    for i in range(1,number+1):
        do_diqu_spider(area, region,i)
        print u"%s 区第 %s 页 over\n" % (region ,i)
    print u"爬下了 %s 区全部 %s 页的小区信息\n" % (region ,number)

for area in szquyu:
    for qy in lhxqquyu:
        pg_spider(area,qy)
print u"爬下所有%s信息\n" % area


