import requests
import pyautogui as pag
import time

##Vars###############################
start_x, start_y = 519, 450
link_x, link_y = 89, 158
editor_x, editor_y = 39, 542
txt_x, txt_y = 784, 106
mini_x, mini_y = 1834, 47 
move_time = 0.25
links_in_page = 10
delta_link = 33
nxt_pg_x, nxt_pg_y = 1819, 780
number_pages = 20
pg_ld_time = 5   
#####################################
# while True:
#     print(pag.position())


def single_link_download(x_pos,y_pos ):

    pag.moveTo(x_pos, y_pos, move_time)
    pag.rightClick()
    pag.moveRel(link_x, link_y, move_time)
    pag.click()
    pag.moveTo(editor_x, editor_y, move_time)
    pag.click()
    pag.moveTo(txt_x, txt_y, move_time)
    pag.click()
    pag.hotkey('ctrl', 'v', interval = 0.15)
    pag.hotkey('enter', interval=0.15)
    pag.moveTo(mini_x, mini_y, move_time)
    pag.click()

for _p in range(number_pages):

    x_pos, y_pos = start_x, start_y

    for _ in range(links_in_page):

        single_link_download(x_pos, y_pos)

        y_pos += delta_link

    pag.moveTo(nxt_pg_x, nxt_pg_y, move_time)
    
    pag.click()

    time.sleep(pg_ld_time)
    


