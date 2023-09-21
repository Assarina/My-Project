async function getData() {
    try {
        const res = await fetch('http://localhost:5000/scoreBoard')
        if (res.ok) {
            const datas = await res.json()
            datas.sort(
                (a, b) => b.win - a.win
            )
            return datas
        } else {
            console.log('Can not get Data')
        }
    } catch (err) {
        console.log(err)
    }
}

export {getData}