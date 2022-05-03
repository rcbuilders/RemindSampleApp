package com.remind.sampleapp.sorted_with

data class UserScore (val name: String, val score: Int) {

    companion object {
        fun fetchList(): List<UserScore> {
            return listOf(
                UserScore("아이유", 64),
                UserScore("사나", 31),
                UserScore("Zivana", 87),
                UserScore("조이", 93),
                UserScore("미나", 76),
                UserScore("Alanget", 98),
                UserScore("satucar", 34),
                UserScore("슬기", 65),
                UserScore("Celenor", 98),
                UserScore("솔라", 78),
                UserScore("정연", 88),
                UserScore("77farince", 56),
                UserScore("하니", 49),
                UserScore("페이", 80),
                UserScore("23Lernial", 77),
                UserScore("초코", 98),
                UserScore("가희", 36),
                UserScore("mosink", 73),
                UserScore("슈슈", 81),
                UserScore("88Potiarir", 98),
                UserScore("스즈", 94),
                UserScore("Minerth", 44),
                UserScore("solvod", 76),
                UserScore("시아", 44),
                UserScore("네모", 36),
                UserScore("라면", 98),
                UserScore("로드", 76),
                UserScore("슈콤", 95),
                UserScore("쉬드", 98),
                UserScore("12시에만나", 43),
                UserScore("리사로지", 77),
                UserScore("라비레기", 88),
                UserScore("링고릴리", 64),
                UserScore("33로빈", 98),
                UserScore("배키", 78),
                UserScore("불로불사", 76),
                UserScore("핑키", 45),
            )
        }
    }

}