name = "selectedicon.png";
img = imread(name);

alpha = rgb2gray(img) < 255;

alpha = double(alpha);

imwrite(img, "radio"+name, 'png', 'Alpha', alpha);