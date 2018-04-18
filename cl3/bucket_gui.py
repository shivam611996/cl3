'''
NOTE: first install this commands in your ubuntu to setup your system
    1) " sudo pip install --upgrade google-cloud-storage "
    2) " export CLOUD_SDK_REPO="cloud-sdk-$(lsb_release -c -s)" "
    3) " echo "deb http://packages.cloud.google.com/apt $CLOUD_SDK_REPO main" | sudo tee -a /etc/apt/sources.list.d/google-cloud-sdk.list "
    4) " curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add - "
    5) " sudo apt-get update "
    6) " sudo apt-get install google-cloud-sdk "
    7) " sudo pip install Tkinter "
    8) " sudo pip install tkMessageBox "
    
then make key.json file from your google cloud platform and store it in same folder as your this code is.

then you are ready to go.

how to execute this code

1) go to that folder in which the program is stored using cd folder_name

2) open terminal ("ctrl+alt+t")

3) type the folloing commands to run this program
    "python bucket_gui.py"
    and hit enter
    
done

NOTE : make sure that you have logged in in google cloud platform and "gcloud init" command is configured in your gcp(google cloud platform) terminal.

before execution make sure you comment remove_bucket() wala part or else bucket is removed as soon as program executed...
or you'll not be able to view any results on google cloud platform ..
after execution go to your google cloud platform and see if changes are happend or not.
'''


#import Tkinter
#import tkMessageBox
from tkinter import *
import os

import google.cloud.storage




root = Tk()

the_lable = Label(root, text="Welcome To GOOGLE APP NETIZENS")
the_lable.grid()

topFrame = Frame(root,width=500,height=50)
topFrame.grid()

bottomFrame = Frame(root,width=500,height=800)
bottomFrame.grid()


def create_bucket():
    os.system("gsutil mb gs://sjio")
    print("bucket created")    
    
def list_objects():
    print("listing objects in created bucket..")
    os.system("gsutil ls gs://sjio/")
    print("ls command executed")


def list_buckets():
    print("list of buckets ")
    os.system("gsutil ls")

def insert_file():
    print("inserting file into bucket")
    os.system("touch text.txt")
    os.system("gsutil cp text.txt gs://sjio/")
    print("file inserted")

def remove_bucket():
    os.system("gsutil rm -r gs://sjio/")
    print("bucket removed")
    
   
#lable_1=Label(topFrame,text="To create Bucket on google cloud press create botton ")
#lable_1.grid(row=1,column=0)
button_1 = Button(bottomFrame,text="Create Bucket", fg="green",command = create_bucket)
button_1.grid(row=1,column=1)

#lable_2=Label(topFrame,text="To execute ls command on google cloud press ls Botton ")
#lable_2.grid(row=3,column=0)
button_2 = Button(bottomFrame,text="list objects", fg="green",command = list_objects)
button_2.grid(row=2,column=1)

button_3 = Button(bottomFrame,text="list buckets", fg="green",command = list_buckets)
button_3.grid(row=3,column=1)

button_4 = Button(bottomFrame,text="insert a file into a bucket", fg="green",command = insert_file)
button_4.grid(row=4,column=1)

button_5 = Button(bottomFrame,text="remove bucket", fg="green",command = remove_bucket)
button_5.grid(row=5,column=1)

root.mainloop()
