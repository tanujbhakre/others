# Computer Vision Raspberry Pi
This project contains python scripts to process images and videos using raspberry pi

## Installing open cv on raspberry pi ##
	https://www.pyimagesearch.com/2015/07/27/installing-opencv-3-0-for-both-python-2-7-and-python-3-on-your-raspberry-pi-2/

## Detecting faces in image ##
	python3 detect_faces.py --prototxt deploy.prototxt.txt \
	--model res10_300x300_ssd_iter_140000.caffemodel --image sample2.jpeg

## Detecting faces in video ##
	python3 detect_faces_video.py --prototxt deploy.prototxt.txt \
		--model res10_300x300_ssd_iter_140000.caffemodel
