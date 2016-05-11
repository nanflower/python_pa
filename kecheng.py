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

def do_xiaoqu_spider(region,number):
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
    #d="d="+soup.find('div',{'class':'page-box house-lst-page-box'}).get('page-data')
    #exec(d)
    #print soup.find_all('div','price').find_all('span','num')
    #total_pages=d['totalPage']
    xiaoqu_list=soup.findAll('div',{'class':'info-panel'})
    for xq in xiaoqu_list:
        info_dict={}
        href=xq.find('a')
        if not href:
            continue
            """
        info_dict.update({u'链接':href.attrs['title']})
        print info_dict
        print href.attrs['title']
        content=xq.find('h2').text.split(' ')
        if content:
            info_dict.update({u'小区名称':content[0]})
            """
        #    info_dict.update({u'户型':content[1]})
        #    info_dict.update({u'面积':content[2]})
        locate = xq.find('span', 'region')
        print locate.get_text()
        zone = xq.find('span','zone')
        print zone.get_text()
        meter = xq.find('span','meters')
        print meter.get_text()
        price = xq.find('div', 'price')
        print price.get_text()
        f = open('buxin.txt','a')
        f.write(locate.get_text()+" "+zone.get_text()+" "+meter.get_text()+" "+price.get_text()+"\n")
    """
    xiaoqu_list=soup.findAll('div',{'class':'info-panel'})
    for xq in xiaoqu_list:
        info_dict={}
        info_dict.update({u'小区名称':xq.find('a').text})
        content=unicode(xq.find('div',{'class':'con'}).renderContents().strip())
        info=re.match(r".+>(.+)</a>.+>(.+)</a>.+</span>(.+)<span>.+</span>(.+)",content)
        print info_dict
        if info:
            info=info.groups()
            info_dict.update({u'大区域':info[0]})
            info_dict.update({u'小区域':info[1]})
            info_dict.update({u'小区户型':info[2]})
            info_dict.update({u'建造时间':info[3][:4]})
    """

    #threads=[]
    #for i in range(total_pages):
    #    url_page=u"http://bj.lianjia.com/xiaoqu/pg%drs%s/" % (i+1,region)
    #    t=threading.Thread(target=xiaoqu_spider,args=(db_xq,url_page))
    #    threads.append(t)
    #for t in threads:
    #    t.start()
    #for t in threads:
    #    t.join()
    print u"爬下了 %s 区全部的小区信息" % region

def pg_spider(region, number):
    for i in range(1,number):
        do_xiaoqu_spider(region,i)

pg_spider("buxin",16)


