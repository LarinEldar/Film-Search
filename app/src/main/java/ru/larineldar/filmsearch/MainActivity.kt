package ru.larineldar.filmsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createClickListener()

        val films = listOf<Film>(
            Film("Зеленая миля", R.drawable.thegreenmile, "Пол Эджкомб — начальник блока смертников в тюрьме «Холодная гора», каждый из узников которого однажды проходит «зеленую милю» по пути к месту казни. Пол повидал много заключённых и надзирателей за время работы. Однако гигант Джон Коффи, обвинённый в страшном преступлении, стал одним из самых необычных обитателей блока."),
            Film("Побег из шоушенка", R.drawable.theshawshankredemtion, "Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, обладающий живым умом и доброй душой, находит подход как к заключённым, так и к охранникам, добиваясь их особого к себе расположения."),
            Film("Список Шиндлера", R.drawable.schindlerslist, "Фильм рассказывает реальную историю загадочного Оскара Шиндлера, члена нацистской партии, преуспевающего фабриканта, спасшего во время Второй мировой войны почти 1200 евреев."),
            Film("Властелин колец: Возвращение короля", R.drawable.thelordoftheringsthereturnoftheking, "Повелитель сил тьмы Саурон направляет свою бесчисленную армию под стены Минас-Тирита, крепости Последней Надежды. Он предвкушает близкую победу, но именно это мешает ему заметить две крохотные фигурки — хоббитов, приближающихся к Роковой Горе, где им предстоит уничтожить Кольцо Всевластья."),
            Film("1+1", R.drawable.intouchables, "Пострадав в результате несчастного случая, богатый аристократ Филипп нанимает в помощники человека, который менее всего подходит для этой работы, – молодого жителя предместья Дрисса, только что освободившегося из тюрьмы. Несмотря на то, что Филипп прикован к инвалидному креслу, Дриссу удается привнести в размеренную жизнь аристократа дух приключений."),
            Film("Криминальное чтиво", R.drawable.pulpfiction, "Двое бандитов Винсент Вега и Джулс Винфилд ведут философские беседы в перерывах между разборками и решением проблем с должниками криминального босса Марселласа Уоллеса."),
            Film("Король Лев", R.drawable.thelionking, "У величественного Короля-Льва Муфасы рождается наследник по имени Симба. Уже в детстве любознательный малыш становится жертвой интриг своего завистливого дяди Шрама, мечтающего о власти."),
            Film("Властелин колец: Две крепости", R.drawable.thelordoftheringsthetwotowers, "Братство распалось, но Кольцо Всевластья должно быть уничтожено. Фродо и Сэм вынуждены довериться Голлуму, который взялся провести их к вратам Мордора. Громадная армия Сарумана приближается: члены братства и их союзники готовы принять бой. Битва за Средиземье продолжается."),
            Film("Форрест Гамп", R.drawable.forrestgump, "Сидя на автобусной остановке, Форрест Гамп — не очень умный, но добрый и открытый парень — рассказывает случайным встречным историю своей необыкновенной жизни."),
            Film("Властелин колец: Братство Кольца", R.drawable.thelordoftheringsthefellowshipofthering, "Сказания о Средиземье — это хроника Великой войны за Кольцо, длившейся не одну тысячу лет. Тот, кто владел Кольцом, получал неограниченную власть, но был обязан служить злу.")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = FilmAdapter()
        adapter.addItems(films)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(SpacingItemDecoration(8))

    }

    fun createClickListener() {
        findViewById<MaterialToolbar>(R.id.top_bar).setOnMenuItemClickListener {
            when(it.itemId){
                R.id.settings -> {
                    Toast.makeText(this, R.string.settings, Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }

        }

        findViewById<BottomNavigationView>(R.id.bottom_bar).setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.favorites -> {
                    Toast.makeText(this, R.string.favorites, Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.watch_later -> {
                    Toast.makeText(this, R.string.watch_later, Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.selection -> {
                    Toast.makeText(this, R.string.selection, Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

}