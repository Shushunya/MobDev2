package ua.kpi.comsys.iv7124

import android.util.Log
import java.util.logging.Logger
import kotlin.math.ceil
import kotlin.random.Random.Default.nextInt

fun contents(){
    // data for tasks
    val studentsStr = "Бортнік Василь - ІВ-72; Чередніченко Владислав - ІВ-73; Гуменюк Олександр - ІВ-71; Корнійчук Ольга - ІВ-71; Киба Олег - ІВ-72; Капінус Артем - ІВ-73; Овчарова Юстіна - ІВ-72; Науменко Павло - ІВ-73; Трудов Антон - ІВ-71; Музика Олександр - ІВ-71; Давиденко Костянтин - ІВ-73; Андрющенко Данило - ІВ-71; Тимко Андрій - ІВ-72; Феофанов Іван - ІВ-71; Гончар Юрій - ІВ-73"
    val points = listOf<Int>(5, 8, 12, 12, 12, 12, 12, 12, 15)

    // common data for tasks
    val groupList = listOf<String>("ІВ-71", "ІВ-72", "ІВ-73")


    fun task1(students: String) : Map<String, List<String>>{
        // Task 1
        // Create dictionary:
        // - key is a group name
        // - value is sorted array with students
        val studentsList = students.split(';')
        val studentsGroups = mutableMapOf<String, List<String>>()

        for (group in groupList) {
            val studentsInGroup = ArrayList<String>()
            for (student in studentsList) {
                if (group in student) {
                    studentsInGroup.add(student.split('-')[0])

                }
            }
            studentsGroups[group] = studentsInGroup.sorted()
        }
        return studentsGroups
    }

    val st = task1(studentsStr)
    println(st)


    fun task2(maxPoints: List<Int>, students:  Map<String, List<String>>) : Map<String, Map<String, List<Int>>> {
        // Task 2
        // Create dictionary:
        // - key is a group name
        // - value is dictionary:
        //   - key is student
        //   - value is array with points (fill it with random values, use function `randomValue(maxValue: Int) -> Int` )
        val studentPoints = mutableMapOf<String, Map<String, List<Int>>>()
        fun randomValue(maxValue: Int): Int {
            return when (nextInt(6)) {
                1 -> ceil(maxValue.toFloat() * 0.7).toInt()
                2 -> ceil(maxValue.toFloat() * 0.9).toInt()
                3, 4, 5 -> maxValue
                else -> 0
            }
        }


        for (group in groupList){
            val randomStudentPoints = mutableMapOf<String, List<Int>>()
            for (student in students.getValue(group)){
                val tmp = maxPoints.map { randomValue(it) }
                randomStudentPoints[student] = tmp
            }
            studentPoints[group] = randomStudentPoints
        }

        return studentPoints

    }

    val marks = task2(points, st)
    println(marks)

    fun task3(studentMarks: Map<String, Map<String, List<Int>>>) : Map<String, Map<String, Int>>{
        // Task 3
        // Create dictionary:
        // - key is a group name
        // - value is dictionary:
        //   - key is student
        //   - value is sum of student's points

        val sumPoints = mutableMapOf<String, Map<String, Int>>()
        for (group in groupList) {
            val studentSumPoints = mutableMapOf<String, Int>()
            for (student in studentMarks.getValue(group)) {
                studentSumPoints[student.key] = student.value.sum()
            }
            sumPoints[group] = studentSumPoints
        }
        return sumPoints
    }

    val sumMarks = task3(marks)
    println(sumMarks)

    fun task4(sumMarks: Map<String, Map<String, Int>>) : Map<String, Float>{
        // Task 4
        // Create dictionary:
        // - key is group name
        // - value is average of all students points

        val groupAvg = mutableMapOf<String, Float>()
        for (group in sumMarks.keys) {
            groupAvg[group] = sumMarks[group]?.values?.average()?.toFloat()!!
        }
        return groupAvg
    }

    val averageGroupMarks = task4(sumMarks)
    println(averageGroupMarks)

    fun task5(sumStudMarks: Map<String, Map<String, Int>>) : Map<String, List<String>>{
        // Task 5
        // Create dictionary:
        // - key is group name
        // - value is array of students that have >= 60 points

        val passedPerGroup = mutableMapOf<String, List<String>>()
        for (group in sumStudMarks.keys) {
            passedPerGroup[group] = sumStudMarks[group]?.filterValues { it >= 60 }?.map { it.key }!!
        }
        return passedPerGroup
    }

    println(task5(sumMarks))

}