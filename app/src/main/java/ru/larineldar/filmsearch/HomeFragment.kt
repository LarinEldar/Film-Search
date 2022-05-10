package ru.larineldar.filmsearch

import android.os.Bundle
import android.transition.Scene
import android.transition.Slide
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class HomeFragment : Fragment() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val root = view.findViewById<ViewGroup>(R.id.fragment_home_root)
        val scene = Scene.getSceneForLayout(root, R.layout.merge_home_fragment_content, requireContext())

        val transition = TransitionSet().apply {
            duration = 300
            addTransition(Slide().apply {
                slideEdge = Gravity.TOP
                addTarget(R.id.search_view)
            })
            addTransition(Slide().apply {
                addTarget(R.id.recycler_view)
            })
        }

        TransitionManager.go(scene, transition)
        initView()
    }

    fun initView(){
        val searchView = requireActivity().findViewById<SearchView>(R.id.search_view)
        val recyclerView = requireActivity().findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = FilmAdapter()

        adapter.addItems(films)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(SpacingItemDecoration(8))

        searchView.setOnClickListener {
            searchView.isIconified = false
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {

                val result = films.filter {
                    it.title.toLowerCase(Locale.getDefault()).contains(newText.toLowerCase(Locale.getDefault()))
                }
                adapter.addItems(result)
                return true
            }

        })
    }
}