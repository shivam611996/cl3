import os
import sys
from tkinter import *

root = Tk()

the_lable = Label(root, text="Welcome To KVM Installer App")
the_lable.grid()

topFrame = Frame(root,width=500,height=50)
topFrame.grid()

bottomFrame = Frame(root,width=500,height=800)
bottomFrame.grid()

def click_install():
	print("Install qemu-kvm and libvirt")
	os.system("sudo apt install -y qemu-kvm libvirt0 libvirt-bin virt-manager bridge-utils")

	os.system("sudo systemctl enable libvirt-bin")
	os.system("sudo apt install -y bridge-utils")
	os.system("sudo gpasswd libvirtd -a shivam")#replace shivam with ur username
	os.system("sudo mkdir /var/lib/libvirt/iso")
	os.system("sudo mv ubuntu-16.04-desktop-amd64.iso /var/lib/libvirt/iso/")
	os.system("sudo chown libvirt-qemu:libvirtd \ /var/lib/libvirt/iso/ubuntu-16.04-desktop-amd64.iso")
	os.system("virt-manager")
#plz.. reboot ur system if kvm is not connected to virt-manager
	print("KVM installed and configuration of VM begins...")
	sys.exit()

button_1 = Button(bottomFrame,text="install KVM", fg="green",command = click_install)
button_1.grid(row=1,column=1)


root.mainloop()


#run : python3 kvm_gui.py
