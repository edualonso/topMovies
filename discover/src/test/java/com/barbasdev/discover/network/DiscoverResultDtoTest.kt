package com.barbasdev.discover.network

import com.barbasdev.discover.network.dto.DiscoverResultDto
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class DiscoverResultDtoTest {

    @Test
    fun canParseResults() {
        with(Gson().fromJson(JSON.RESULTS, DiscoverResultDto::class.java)) {
            assertEquals(1, page)
            assertEquals(10050, totalResults)
            assertEquals(503, totalPages)
            assertEquals(20, results.size)
        }
    }


    object JSON {
        const val RESULTS =
                """
                    {
  "page": 1,
  "total_results": 10050,
  "total_pages": 503,
  "results": [
    {
      "vote_count": 18608,
      "id": 27205,
      "video": false,
      "vote_average": 8.2,
      "title": "Inception",
      "popularity": 30.161,
      "poster_path": "/qmDpIHrmpJINaRKAfWQfftjCdyi.jpg",
      "original_language": "en",
      "original_title": "Inception",
      "genre_ids": [
        28,
        53,
        878,
        9648,
        12
      ],
      "backdrop_path": "/s2bT29y0ngXxxu2IA8AOzzXTRhd.jpg",
      "adult": false,
      "overview": "Cobb, a skilled thief who commits corporate espionage by infiltrating the subconscious of his targets is offered a chance to regain his old life as payment for a task considered to be impossible: \"inception\", the implantation of another person's idea into a target's subconscious.",
      "release_date": "2010-07-14"
    },
    {
      "vote_count": 8464,
      "id": 12444,
      "video": false,
      "vote_average": 7.7,
      "title": "Harry Potter and the Deathly Hallows: Part 1",
      "popularity": 26.757,
      "poster_path": "/maP4MTfPCeVD2FZbKTLUgriOW4R.jpg",
      "original_language": "en",
      "original_title": "Harry Potter and the Deathly Hallows: Part 1",
      "genre_ids": [
        12,
        14,
        10751
      ],
      "backdrop_path": "/8YA36faYlkpfp6aozcGsqq68pZ9.jpg",
      "adult": false,
      "overview": "Harry, Ron and Hermione walk away from their last year at Hogwarts to find and destroy the remaining Horcruxes, putting an end to Voldemort's bid for immortality. But with Harry's beloved Dumbledore dead and Voldemort's unscrupulous Death Eaters on the loose, the world is more dangerous than ever.",
      "release_date": "2010-10-17"
    },
    {
      "vote_count": 9738,
      "id": 10138,
      "video": false,
      "vote_average": 6.7,
      "title": "Iron Man 2",
      "popularity": 26.636,
      "poster_path": "/ArqpkNYGfcTIA6umWt6xihfIZZv.jpg",
      "original_language": "en",
      "original_title": "Iron Man 2",
      "genre_ids": [
        12,
        28,
        878
      ],
      "backdrop_path": "/jxdSxqAFrdioKgXwgTs5Qfbazjq.jpg",
      "adult": false,
      "overview": "With the world now aware of his dual life as the armored superhero Iron Man, billionaire inventor Tony Stark faces pressure from the government, the press and the public to share his technology with the military. Unwilling to let go of his invention, Stark, with Pepper Potts and James 'Rhodey' Rhodes at his side, must forge new alliances – and confront powerful enemies.",
      "release_date": "2010-04-28"
    },
    {
      "vote_count": 8598,
      "id": 20352,
      "video": false,
      "vote_average": 7.2,
      "title": "Despicable Me",
      "popularity": 24.031,
      "poster_path": "/4zHJhBSY4kNZXfhTlmy2TzXD51M.jpg",
      "original_language": "en",
      "original_title": "Despicable Me",
      "genre_ids": [
        16,
        10751,
        35
      ],
      "backdrop_path": "/yo1ef57MEPkEE4BDZKTZGH9uDcX.jpg",
      "adult": false,
      "overview": "Villainous Gru lives up to his reputation as a despicable, deplorable and downright unlikable guy when he hatches a plan to steal the moon from the sky. But he has a tough time staying on task after three orphans land in his care.",
      "release_date": "2010-07-08"
    },
    {
      "vote_count": 9965,
      "id": 11324,
      "video": false,
      "vote_average": 8,
      "title": "Shutter Island",
      "popularity": 21.686,
      "poster_path": "/aZqKsvpJDFy2UzUMsdskNFbfkOd.jpg",
      "original_language": "en",
      "original_title": "Shutter Island",
      "genre_ids": [
        18,
        53,
        9648
      ],
      "backdrop_path": "/bcb3FYtLbuPgNYO4M1IY7rfeMal.jpg",
      "adult": false,
      "overview": "World War II soldier-turned-U.S. Marshal Teddy Daniels investigates the disappearance of a patient from a hospital for the criminally insane, but his efforts are compromised by his troubling visions and also by a mysterious doctor.",
      "release_date": "2010-02-14"
    },
    {
      "vote_count": 5988,
      "id": 10191,
      "video": false,
      "vote_average": 7.7,
      "title": "How to Train Your Dragon",
      "popularity": 20.995,
      "poster_path": "/hIXX3IRFy0InUOmYeWjvhCAgQNj.jpg",
      "original_language": "en",
      "original_title": "How to Train Your Dragon",
      "genre_ids": [
        14,
        12,
        16,
        10751
      ],
      "backdrop_path": "/3eR9HOkPZUbrGWDRdKHeKoiMLc4.jpg",
      "adult": false,
      "overview": "As the son of a Viking leader on the cusp of manhood, shy Hiccup Horrendous Haddock III faces a rite of passage: he must kill a dragon to prove his warrior mettle. But after downing a feared dragon, he realizes that he no longer wants to destroy it, and instead befriends the beast – which he names Toothless – much to the chagrin of his warrior father",
      "release_date": "2010-03-05"
    },
    {
      "vote_count": 6852,
      "id": 12155,
      "video": false,
      "vote_average": 6.5,
      "title": "Alice in Wonderland",
      "popularity": 19.694,
      "poster_path": "/pvEE5EN5N1yjmHmldfL4aJWm56l.jpg",
      "original_language": "en",
      "original_title": "Alice in Wonderland",
      "genre_ids": [
        10751,
        14,
        12
      ],
      "backdrop_path": "/AmCtBQc5KxJfJVdS2TkY4Pc9lPd.jpg",
      "adult": false,
      "overview": "Alice, an unpretentious and individual 19-year-old, is betrothed to a dunce of an English nobleman. At her engagement party, she escapes the crowd to consider whether to go through with the marriage and falls down a hole in the garden after spotting an unusual rabbit. Arriving in a strange and surreal place called 'Underland,' she finds herself in a world that resembles the nightmares she had as a child, filled with talking animals, villainous queens and knights, and frumious bandersnatches. Alice realizes that she is there for a reason – to conquer the horrific Jabberwocky and restore the rightful queen to her throne.",
      "release_date": "2010-03-03"
    },
    {
      "vote_count": 3919,
      "id": 27578,
      "video": false,
      "vote_average": 6,
      "title": "The Expendables",
      "popularity": 19.434,
      "poster_path": "/4gLFKsalwRy0ONzfYaRsKr5wilK.jpg",
      "original_language": "en",
      "original_title": "The Expendables",
      "genre_ids": [
        53,
        12,
        28
      ],
      "backdrop_path": "/x5u73uBylbyCCnkzUGzt3uozqRp.jpg",
      "adult": false,
      "overview": "Barney Ross leads a band of highly skilled mercenaries including knife enthusiast Lee Christmas, a martial arts expert, heavy weapons specialist, demolitionist, and a loose-cannon sniper. When the group is commissioned by the mysterious Mr. Church to assassinate the dictator of a small South American island, Barney and Lee visit the remote locale to scout out their opposition and discover the true nature of the conflict engulfing the city.",
      "release_date": "2010-08-03"
    },
    {
      "vote_count": 3827,
      "id": 24021,
      "video": false,
      "vote_average": 6,
      "title": "The Twilight Saga: Eclipse",
      "popularity": 18.539,
      "poster_path": "/4IIFFKd9vxrlicNbAPS8ONEgIFp.jpg",
      "original_language": "en",
      "original_title": "The Twilight Saga: Eclipse",
      "genre_ids": [
        12,
        14,
        18,
        10749
      ],
      "backdrop_path": "/lKCZYVQQb7Agw6WR25WvQBFKJmK.jpg",
      "adult": false,
      "overview": "Bella once again finds herself surrounded by danger as Seattle is ravaged by a string of mysterious killings and a malicious vampire continues her quest for revenge. In the midst of it all, she is forced to choose between her love for Edward and her friendship with Jacob, knowing that her decision has the potential to ignite the ageless struggle between vampire and werewolf. With her graduation quickly approaching, Bella is confronted with the most important decision of her life.",
      "release_date": "2010-06-23"
    },
    {
      "vote_count": 6824,
      "id": 44214,
      "video": false,
      "vote_average": 7.5,
      "title": "Black Swan",
      "popularity": 18.252,
      "poster_path": "/dQ7uxvsVTspVIsqjfgQj8usJpwX.jpg",
      "original_language": "en",
      "original_title": "Black Swan",
      "genre_ids": [
        18,
        53
      ],
      "backdrop_path": "/t8jFs6cOl1YVSfXXZivWtEb14tF.jpg",
      "adult": false,
      "overview": "A ballet dancer wins the lead in \"Swan Lake\" and is perfect for the role of the delicate White Swan—Princess Odette—but slowly loses her mind as she becomes more and more like Odile, the Black Swan.",
      "release_date": "2010-12-03"
    },
    {
      "vote_count": 6754,
      "id": 10193,
      "video": false,
      "vote_average": 7.7,
      "title": "Toy Story 3",
      "popularity": 18.038,
      "poster_path": "/mMltbSxwEdNE4Cv8QYLpzkHWTDo.jpg",
      "original_language": "en",
      "original_title": "Toy Story 3",
      "genre_ids": [
        16,
        10751,
        35
      ],
      "backdrop_path": "/y2qAjM37QgatWeG84DDtwsZuMW.jpg",
      "adult": false,
      "overview": "Woody, Buzz, and the rest of Andy's toys haven't been played with in years. With Andy about to go to college, the gang find themselves accidentally left at a nefarious day care center. The toys must band together to escape and return home to Andy.",
      "release_date": "2010-06-16"
    },
    {
      "vote_count": 3211,
      "id": 32657,
      "video": false,
      "vote_average": 6.1,
      "title": "Percy Jackson & the Olympians: The Lightning Thief",
      "popularity": 18.01,
      "poster_path": "/5NhyXkodMzDRW8uqtPqlxJsoBhf.jpg",
      "original_language": "en",
      "original_title": "Percy Jackson & the Olympians: The Lightning Thief",
      "genre_ids": [
        12,
        14,
        10751
      ],
      "backdrop_path": "/uHQzRMqhs1bA1fLEP6J1Qc19Nfg.jpg",
      "adult": false,
      "overview": "Accident prone teenager, Percy discovers he's actually a demi-God, the son of Poseidon, and he is needed when Zeus' lightning is stolen. Percy must master his new found skills in order to prevent a war between the Gods that could devastate the entire world.",
      "release_date": "2010-02-01"
    },
    {
      "vote_count": 5073,
      "id": 38757,
      "video": false,
      "vote_average": 7.5,
      "title": "Tangled",
      "popularity": 17.889,
      "poster_path": "/re6AOJbhBk9FIK3knwU6rYlbPDx.jpg",
      "original_language": "en",
      "original_title": "Tangled",
      "genre_ids": [
        16,
        10751
      ],
      "backdrop_path": "/uFg02Gt69UM6Ouam4slMdD0s029.jpg",
      "adult": false,
      "overview": "When the kingdom's most wanted-and most charming-bandit Flynn Rider hides out in a mysterious tower, he's taken hostage by Rapunzel, a beautiful and feisty tower-bound teen with 70 feet of magical, golden hair. Flynn's curious captor, who's looking for her ticket out of the tower where she's been locked away for years, strikes a deal with the handsome thief and the unlikely duo sets off on an action-packed escapade, complete with a super-cop horse, an over-protective chameleon and a gruff gang of pub thugs.",
      "release_date": "2010-02-03"
    },
    {
      "vote_count": 4050,
      "id": 45269,
      "video": false,
      "vote_average": 7.7,
      "title": "The King's Speech",
      "popularity": 16.094,
      "poster_path": "/nkFTbA1XjKWo9LCTaV1hV2Lsgr1.jpg",
      "original_language": "en",
      "original_title": "The King's Speech",
      "genre_ids": [
        18,
        36
      ],
      "backdrop_path": "/pJnU0JArW4aLcOoS9u2vQHDGJpK.jpg",
      "adult": false,
      "overview": "The King's Speech tells the story of the man who became King George VI, the father of Queen Elizabeth II. After his brother abdicates, George ('Bertie') reluctantly assumes the throne. Plagued by a dreaded stutter and considered unfit to be king, Bertie engages the help of an unorthodox speech therapist named Lionel Logue. Through a set of unexpected techniques, and as a result of an unlikely friendship, Bertie is able to find his voice and boldly lead the country into war.",
      "release_date": "2010-09-06"
    },
    {
      "vote_count": 3740,
      "id": 20526,
      "video": false,
      "vote_average": 6.3,
      "title": "TRON: Legacy",
      "popularity": 15.811,
      "poster_path": "/vllvystwQjmXzy5OvBKnGl1JREF.jpg",
      "original_language": "en",
      "original_title": "TRON: Legacy",
      "genre_ids": [
        12,
        28,
        878
      ],
      "backdrop_path": "/6hquJfTEM97bqMekCOPO50SLbzr.jpg",
      "adult": false,
      "overview": "Sam Flynn, the tech-savvy and daring son of Kevin Flynn, investigates his father's disappearance and is pulled into The Grid. With the help of a mysterious program named Quorra, Sam quests to stop evil dictator Clu from crossing into the real world.",
      "release_date": "2010-12-10"
    },
    {
      "vote_count": 6338,
      "id": 23483,
      "video": false,
      "vote_average": 7.1,
      "title": "Kick-Ass",
      "popularity": 15.438,
      "poster_path": "/yZFrniO6qSwjTCosStXweYtczGT.jpg",
      "original_language": "en",
      "original_title": "Kick-Ass",
      "genre_ids": [
        28,
        80
      ],
      "backdrop_path": "/ngPkB9K8ZpIjv8HpIC8ExqAfG6H.jpg",
      "adult": false,
      "overview": "Dave Lizewski is an unnoticed high school student and comic book fan who one day decides to become a super-hero, even though he has no powers, training or meaningful reason to do so.",
      "release_date": "2010-03-22"
    },
    {
      "vote_count": 4951,
      "id": 37799,
      "video": false,
      "vote_average": 7.2,
      "title": "The Social Network",
      "popularity": 15.242,
      "poster_path": "/ok5Wh8385Kgblq9MSU4VGvazeMH.jpg",
      "original_language": "en",
      "original_title": "The Social Network",
      "genre_ids": [
        18
      ],
      "backdrop_path": "/5NGK7YnypChaA9E2wADXEptU4Iz.jpg",
      "adult": false,
      "overview": "On a fall night in 2003, Harvard undergrad and computer programming genius Mark Zuckerberg sits down at his computer and heatedly begins working on a new idea. In a fury of blogging and programming, what begins in his dorm room as a small site among friends soon becomes a global social network and a revolution in communication. A mere six years and 500 million friends later, Mark Zuckerberg is the youngest billionaire in history... but for this entrepreneur, success leads to both personal and legal complications.",
      "release_date": "2010-10-01"
    },
    {
      "vote_count": 1101,
      "id": 37821,
      "video": false,
      "vote_average": 5.8,
      "title": "Killers",
      "popularity": 15.134,
      "poster_path": "/bQHs6vyYwvxYvWEIi1G9rk4qNdw.jpg",
      "original_language": "en",
      "original_title": "Killers",
      "genre_ids": [
        28,
        35,
        53,
        10749
      ],
      "backdrop_path": "/tcv1qOo2k6ifKjz9hD2oTYtrsOq.jpg",
      "adult": false,
      "overview": "When an elite assassin marries a beautiful computer whiz after a whirlwind romance, he gives up the gun and settles down with his new bride. That is, until he learns that someone from his past has put a contract out on his life.",
      "release_date": "2010-06-04"
    },
    {
      "vote_count": 2779,
      "id": 27576,
      "video": false,
      "vote_average": 6.2,
      "title": "Salt",
      "popularity": 15.132,
      "poster_path": "/956xMjH4sPoqimqoLOP6AI19mjm.jpg",
      "original_language": "en",
      "original_title": "Salt",
      "genre_ids": [
        28,
        9648,
        53
      ],
      "backdrop_path": "/2WkmqO1qjWEs3hySK56q3ES7W7b.jpg",
      "adult": false,
      "overview": "As a CIA officer, Evelyn Salt swore an oath to duty, honor and country. Her loyalty will be tested when a defector accuses her of being a Russian spy. Salt goes on the run, using all her skills and years of experience as a covert operative to elude capture. Salt's efforts to prove her innocence only serve to cast doubt on her motives, as the hunt to uncover the truth behind her identity continues and the question remains: \"Who is Salt?\"",
      "release_date": "2010-07-21"
    },
    {
      "vote_count": 3014,
      "id": 10192,
      "video": false,
      "vote_average": 6.1,
      "title": "Shrek Forever After",
      "popularity": 14.421,
      "poster_path": "/enqVwbUu6OX5LHq0fuk7sLT35zy.jpg",
      "original_language": "en",
      "original_title": "Shrek Forever After",
      "genre_ids": [
        35,
        12,
        14,
        16,
        10751
      ],
      "backdrop_path": "/aD8iOuLlEiMfVlHTnAe15fZLo0S.jpg",
      "adult": false,
      "overview": "A bored and domesticated Shrek pacts with deal-maker Rumpelstiltskin to get back to feeling like a real ogre again, but when he's duped and sent to a twisted version of Far Far Away—where Rumpelstiltskin is king, ogres are hunted, and he and Fiona have never met—he sets out to restore his world and reclaim his true love.",
      "release_date": "2010-05-16"
    }
  ]
}
"""
    }
}