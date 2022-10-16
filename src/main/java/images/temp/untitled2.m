[img, ~, alpha] = imread("roomba128.png");

img_gray = rgb2gray(img);
img_gray(img_gray < 255) = 220;

imwrite(img_gray, "roombatransparent.png", "png", "Alpha", alpha);