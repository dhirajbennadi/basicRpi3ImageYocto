# this should get the wifi configured on boot

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
PROVIDES="my-init"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "\
  file://start-wifi.sh \
  "

# Not Needed for now @dhirajbennadi
#inherit update-rc.d
#INITSCRIPT_PACKAGES = "${PN}"
#INITSCRIPT_NAME:${PN} = "start-wifi.sh"

#ntp-start.sh

INITSCRIPT_PACKAGES                 = "${PN}"
#INITSCRIPT_NAME_${PN}               = "ntp-start.sh"
#INITSCRIPT_PARAMS_${PN}             = "start 99 2 3 4 5 ."
INITSCRIPT_NAME_${PN}   = "start-wifi.sh"
INITSCRIPT_PARAMS_${PN} = "start 1 2 3 4 5 ."

inherit update-rc.d

do_configure () {
	:
}

do_compile () {
    :
}

do_install () {
  install -m 0755 -d ${D}${sysconfdir}/init.d
  install -m 0755 ${THISDIR}/${PN}/start-wifi.sh ${D}${sysconfdir}/init.d

  # Dont need this for now. Change of Strategy
  #install -m 0755 ${THISDIR}/${PN}/ntp-start.sh ${D}${sysconfdir}/init.d
}