#--umport random
import sys,time,random
import urllib,urllib2

user_gif_url = "http://localhost:8000/user.gif"


def rand(values=[]):
    rnd = random.randint(0, len(values) - 1)
    return values[rnd]

def rand_arr(arr=[]):
    rnd = random.randint(1, len(arr) - 1)
    return arr[0:rnd]

def tracker(ptc=None, uid=None):
    params = {
        "_ptc": ptc,
        "_puid": uid,
        "_pua_gender": rand(['m', 'f']),
        "_pua_isvip": rand(['t', 'f']),
        "_pua_viplevel": rand(['1', '2', '3', '4', '5']),
        "_pua_platform": ",".join(rand_arr(['aos', 'ios', 'web'])),
        "_puan_age": random.randint(1, 80)
    }

    url = user_gif_url + "?" + urllib.urlencode(params)
    print url
    urllib2.urlopen(url)


if __name__ == "__main__":
    tenant_codes = ["jgg","bill"]

    while True:
        time.sleep(10)
        uid = str(random.randint(1,10000)).rjust(6, "0")
        tracker(rand(tenant_codes), uid)
