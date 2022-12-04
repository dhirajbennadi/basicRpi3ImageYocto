#!/bin/bash
# Script to clean build yocto recipes
# Authors: Dhiraj Bennadi

cd buildroot

pwd

echo "Cleaning sstate cache directory"
rm -rf sstate-cache/

echo "Cleaning tmp directory"
rm -rf tmp/