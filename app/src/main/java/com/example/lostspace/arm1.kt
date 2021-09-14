package com.example.lostspace

import android.graphics.Bitmap
import android.graphics.Canvas

class arm1 {

    private var x: Int = 0
    private var y: Int = 0
    private val arm = arrayOfNulls<Bitmap>(5)
    private var refer = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    private var animate = 0
    private var fire = false

    fun draw(c: Canvas) {
        c.drawBitmap(refer, x.toFloat(), y.toFloat(), null)
    }

    fun setpositions(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    fun render() {
        if (fire)
            setImage(arm[animate])
        else
            setImage(arm[0])

        if (animate < 4)
            animate++
        else
            animate = 0
    }

    fun setImage(b: Bitmap?) {
        this.refer = b
    }

    fun getFrames(arm: Array<Bitmap>) {
        System.arraycopy(arm, 0, this.arm, 0, arm.size)

    }

    fun getFire(fire: Boolean) {
        this.fire = fire
    }

}
