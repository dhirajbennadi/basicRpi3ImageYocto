SUMMARY = "A custom image that supports the RPI 3B"
LICENSE = "MIT"
ENABLE_UART = "1"
DISTRO_FEATURES_append = " wifi"
IMAGE_FEATURES_append = " ssh-server-openssh splash"
#IMAGE_FEATURES_remove = "debug-tweaks"
IMAGE_INSTALL_append = " wpa-supplicant \
                         python3  \
                         ntpdate \
                         vim \  
                         net-tools \
                         paho-mqtt-c \
                         cronie \
                         htop \
                         bash \
                         my-init \
                         mqtt \
                         mqtt-dhiraj \
                         socket \
                         ntp-dhiraj \
                         "

inherit core-image

#CORE_IMAGE_EXTRA_INSTALL += "aesd-assignments"
#CORE_IMAGE_EXTRA_INSTALL += "openssh"

inherit extrausers

# See https://docs.yoctoproject.org/singleindex.html#extrausers-bbclass
# We set a default password of root to match our busybox instance setup
# Don't do this in a production image
# PASSWD below is set to the output of
# printf "%q" $(mkpasswd -m sha256crypt root) to hash the "root" password
# string

PASSWD = "\$5\$2WoxjAdaC2\$l4aj6Is.EWkD72Vt.byhM5qRtF9HcCM/5YpbxpmvNB5"
EXTRA_USERS_PARAMS = "usermod -p '${PASSWD}' root;"
