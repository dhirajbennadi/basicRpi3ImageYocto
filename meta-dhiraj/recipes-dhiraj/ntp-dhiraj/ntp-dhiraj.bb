# See http://git.yoctoproject.org/cgit.cgi/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Set this with the path to your assignments rep.  Use ssh protocol and see lecture notes
# about how to setup ssh-agent for passwordless access
#BB_STRICT_CHECKSUM = "0"
#SRC_URI[sha256sum] = "https://github.com/dhirajbennadi/aesd-final-project.git;protocol=https;branch=main"

SRC_URI = "git://git@github.com/dhirajbennadi/aesd-final-project.git;protocol=ssh;branch=main"

#SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-dhirajbennadi;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"

# Set to reference a specific commit hash in your assignment repo
SRCREV = "210cd9e1314e0836bc2aa84ee1aa0a9fd8797c14"

# This sets your staging directory based on WORKDIR, where WORKDIR is defined at 
# https://www.yoctoproject.org/docs/latest/ref-manual/ref-manual.html#var-WORKDIR
# We reference the "server" directory here to build from the "server" directory
# in your assignments repo

S = "${WORKDIR}/git/ntp/"

FILES_${PN} += "${bindir}/ntpApp"

INITSCRIPT_PACKAGES     = "${PN}"
INITSCRIPT_NAME_${PN}   = "ntp-start.sh"
INITSCRIPT_PARAMS_${PN} = "start 1 2 3 4 5 ."

inherit update-rc.d

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
  # install destination dir /usr/bin (bindir) 
  install -m 0755 -d ${D}${bindir}
  # install aesdsocket to /usr/bin
  install -m 0755 ${S}/ntpApp ${D}${bindir}/
  install -m 0755 -d ${D}${sysconfdir}/ntpApp

  # Configuration File
  install -m 0755 ${S}/ntp.conf ${D}${bindir}/
  install -m 0755 -d ${D}${sysconfdir}/ntp.conf

  #Start Up Script
  install -m 0755 -d ${D}${sysconfdir}/init.d
  install -m 0755 ${S}/ntp-start.sh ${D}${sysconfdir}/init.d
}
