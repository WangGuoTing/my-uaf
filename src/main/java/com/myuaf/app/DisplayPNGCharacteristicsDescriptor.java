package com.example.defclass;




public class DisplayPNGCharacteristicsDescriptor {
    public long width;	//required
    public long height;	//required
    public /*octet*/int bitDepth;		//required
    public /*octet*/int colorType;		//required
    public /*octet*/int compression;	//required
    public /*octet*/int filter;		//required	
    public /*octet*/int interlace;		//required
    public rgbPalletteEntry[]     plte;
};

class rgbPalletteEntry {
    public short r;
    public short g;
    public short b;
};