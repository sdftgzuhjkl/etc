TARGETS = console-setup resolvconf mountkernfs.sh hostname.sh udev keyboard-setup hwclock.sh urandom networking mountdevsubfs.sh checkroot.sh mountall.sh checkfs.sh checkroot-bootclean.sh procps mountnfs.sh mountnfs-bootclean.sh bootmisc.sh mountall-bootclean.sh kmod
INTERACTIVE = console-setup udev keyboard-setup checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
hwclock.sh: mountdevsubfs.sh
urandom: hwclock.sh
networking: resolvconf mountkernfs.sh urandom procps
mountdevsubfs.sh: mountkernfs.sh udev
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh keyboard-setup
mountall.sh: checkfs.sh checkroot-bootclean.sh
checkfs.sh: checkroot.sh
checkroot-bootclean.sh: checkroot.sh
procps: mountkernfs.sh udev
mountnfs.sh: networking
mountnfs-bootclean.sh: mountnfs.sh
bootmisc.sh: mountnfs-bootclean.sh mountall-bootclean.sh udev checkroot-bootclean.sh
mountall-bootclean.sh: mountall.sh
kmod: checkroot.sh
