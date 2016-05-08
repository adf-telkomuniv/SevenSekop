[im,mp] = imread('Kartlar.png');
ii2 = 0;
close all;
ii3 = 0;
for ii=98:98:392
    jj2 = 0;
    jj3 = 0;
    for jj=73:73:950        
%         figure
%         imshow(im(ii2+1:ii,jj2+1:jj,:),mp);
        imwrite(im(ii2+1:ii,jj2+1:jj,:),mp,[num2str(ii3) '-' num2str(jj3) '.png'],'png')
        jj2=jj;
        jj3 = jj3+1;
    end
    ii2=ii;
    ii3 = ii3+1;
end
% imwrite(im(1:98,1:73,:),mp,'1.png','png')