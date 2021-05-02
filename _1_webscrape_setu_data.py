import requests
import pyautogui as pag
import time

##Vars###############################
move_time = 0.25
links_in_page = 10
number_pages = 280
pg_ld_time = 5 

PC = True
if PC:
    start_x, start_y = 519, 450
    link_x, link_y = 89, 158
    editor_x, editor_y = 39, 542
    txt_x, txt_y = 784, 106
    mini_x, mini_y = 1834, 47 
    delta_link = 33
    nxt_pg_x, nxt_pg_y = 1819, 780
else:
    start_x, start_y = 619, 649
    link_x, link_y = 119, 227
    editor_x, editor_y = 1221, 1467
    txt_x, txt_y = 618, 79
    mini_x, mini_y = 2050, 25 
    delta_link = 49
    nxt_pg_x, nxt_pg_y = 2114, 1142
  
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
    


