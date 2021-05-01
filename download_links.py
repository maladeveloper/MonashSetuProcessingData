import os
from pprint import pprint
import requests
import time
##Vars########################
raw_links_file = "raw_links.txt"
all_links_file  = "all_links.txt"
down_links_file  = "downloaded_links.txt"
start_id = "SelectedIDforPrint="
end_id = "&ReportType"
save_ftype = ".html"
download_directory = "DownloadedReports/"
sleep_secs = 5
################################
if not os.path.exists(download_directory):
    
    os.makedirs(download_directory)

with open(raw_links_file, "r") as fp:
    
    all_links = set(fp.readlines())

with open(all_links_file, "w") as fp:

    for link in all_links:

        fp.write(link)

with open(down_links_file, "r") as fp:
    
    try:
        
        down_links = set(fp.readlines())#links that have already been downloaded
    
    except:

        down_links = set()

links = all_links - down_links

links = [link.strip("\n") for link in links]

doc_counter = 0 
##Make a request for each link
for url_link in links:

    doc_counter += 1 

    print(f'''Requesting link number {doc_counter} out of {len(links)}...''')
    doc = requests.get(url_link)
    
    doc_name = (url_link.split(end_id)[0]).split(start_id)[-1]
    print(doc_name)

    print("Saving document.")
    with open(download_directory + doc_name + save_ftype, "wb") as fp:

        fp.write(doc.content)
    
    print("Adding to processed links.")
    with open(down_links_file, "a") as fp:

        fp.write(url_link + "\n")
    
    print(f'''Sleep (to not overload server) for {sleep_secs} seconds.''')
    time.sleep(sleep_secs)
    
    print("-------------------------------------------------------------")


    