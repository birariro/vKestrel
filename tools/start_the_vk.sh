#!/bin/sh

docker stop vk && docker rm vk && docker rmi k4keye/vkestrel && docker pull k4keye/vkestrel && docker run -d -p 8791:8791 --network  jhlee_vk-net --name vk k4keye/vkestrel