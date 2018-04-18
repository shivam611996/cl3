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
    "python bucket.py"
    and hit enter
    
done

NOTE : make sure that you have logged in in google cloud platform and "gcloud init" command is configured in your gcp(google cloud platform) terminal.



before execution make sure you comment remove_bucket() wala part or else bucket is removed as soon as program executed...
or you'll not be able to view any results on google cloud platform ..
after execution go to your google cloud platform and see if changes are happend or not.
'''

import os

import google.cloud.storage


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
    
create_bucket()
list_buckets()
list_objects()
insert_file()
list_objects()
remove_bucket()
