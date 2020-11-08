package ua.kpi.comsys.iv7124

import java.util.*


// task 3: OT - Oleksandra Tkachenko IV-71
// task 4: Додайте три властивості типу UInt в клас TimeXY, для представлення годин, хвилин та секунд.
class TimeOT (hours: Int, minutes: Int, seconds: Int) {
    // task 5: Додайте методи ініціалізації:
    //з заданим об'єктом типу Date.

    //з нульовими значеннями за замовчанням;
    //з заданим набором значень (години, хвилини, секунди)
    // (перевірте вхідні значення – години ∈ [0, 23], хвилини ∈ [0, 59], секунди ∈ [0, 59])
    val h: Int = if (hours in 0..23) hours else 0
    val m: Int = if (minutes in 0..59) minutes else 0
    val s: Int = if (seconds in 0..59) seconds else 0
    constructor() : this(0, 0, 0)
    constructor(time: Date) : this(time.hours, time.minutes, time.seconds)

    override fun toString(): String {
        val zz = if (h < 12) "AM" else "PM"
        val h12 = h % 12
        return "%02d:%02d:%02d %s".format(h12, m, s, zz)
    }

    fun add(that: TimeOT): TimeOT {
        val s = this.s + that.s
        val m = this.m + that.m + (if (s > 59) 1 else 0)
        val h = this.h + that.h + (if (m > 59) 1 else 0)
        return TimeOT(
            h % (23 + 1),
            m % (59 + 1),
            s % (59 + 1))
    }

    fun sub(that: TimeOT): TimeOT {
        val s = this.s - that.s
        val m = this.m - that.m - (if (s < 0) 1 else 0)
        val h = this.h - that.h - (if (m < 0) 1 else 0)
        return TimeOT(
            (h + 23 + 1) % (23 + 1),
            (m + 59 + 1) % (59 + 1),
            (s + 59 + 1) % (59 + 1))
    }
}

fun addTimes(t1: TimeOT, t2: TimeOT): TimeOT {return t1.add(t2)}

fun subTimes(t1: TimeOT, t2: TimeOT): TimeOT {return t1.sub(t2)}