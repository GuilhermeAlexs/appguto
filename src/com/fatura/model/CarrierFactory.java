package com.fatura.model;

import android.util.Log;

/*
 * São Paulo – DDD 11 a 19 Vivo (96 a 99), Claro (91 a 94) Tim (81 a 87)

Rio de Janeiro e Espírito Santo – DDD 21 a 28 Vivo (96 a 99), Claro (91 a 94, 82 a 84 81 a 82) OI (86 a 88) Tim (80 a 83)

Paraná e Santa Catarina – DDD 41 a 49 Tim (96 a 99), Vivo (91 a 94) Claro (87 a 88) Brasil Telecom (84 a 85)

Rio Grande do Sul – DDD 51 a 55 Vivo (96 a 99), Claro (91 a 94) Tim (81 a 82) Brasil Telecom (84 a 85)

Minas Gerais – DDD 31 a 38 Telemig Celular (96 a 99), Tim (91 a 94) OI (86 a 88) Claro (82 a 84)

Bahia e SE – DDD 71 a 79 Vivo (96 a 99), Tim (91 a 94) OI (86 a 88) Claro (82 a 84)

Nordeste - DDD 81 a 89 Tim (96 a 99), Claro (91 a 94) OI (86 a 88) Vivo (82 a 84)

Centro Oeste – DDD 61 a 69 Vivo (96 a 99), Claro (91 a 94) Tim (81 a 82) Brasil

Amazonia – DDD 91 a 99 Amazonia Celular (96 a 99), Vivo (91 a 94) OI (86 a 88) Tim (80 a 83)
 */
public class CarrierFactory {
	public static Carrier createCarrier(int code){
		if(code == 21)
			return new Carrier(Carrier.CLARO);
		else if(code == 14)
			return new Carrier(Carrier.OI);
		else if(code == 41)
			return new Carrier(Carrier.TIM);

		return null;
	}

	public static Carrier createCarrier(int ddd, String number){
		int prefix = Integer.parseInt(number.substring(0, 2));

		if(ddd >= 11 && ddd <= 19){
			if(prefix >= 96 && prefix <= 99)
				return new Carrier(Carrier.VIVO);
			else if(prefix >= 91 && prefix <= 94)
				return new Carrier(Carrier.CLARO);
			else if(prefix >= 81 && prefix <= 87)
				return new Carrier(Carrier.TIM);
		}else if(ddd >= 21 && ddd <= 28){
			if(prefix >= 96 && prefix <= 99)
				return new Carrier(Carrier.VIVO);
			else if((prefix >= 91 && prefix <= 94) || (prefix >= 81 && prefix <= 84))
				return new Carrier(Carrier.CLARO); 
			else if(prefix >= 80 && prefix <= 83)
				return new Carrier(Carrier.TIM);
			else if(prefix >= 86 && prefix <= 88)
				return new Carrier(Carrier.OI);
		}else if(ddd >= 41 && ddd <= 49){
			if(prefix >= 91 && prefix <= 94)
				return new Carrier(Carrier.VIVO);
			else if(prefix >= 87 && prefix <= 88)
				return new Carrier(Carrier.CLARO); 
			else if(prefix >= 96 && prefix <= 99)
				return new Carrier(Carrier.TIM);
			else if(prefix >= 84 && prefix <= 85)
				return new Carrier(Carrier.OI);
		}else if(ddd >= 51 && ddd <= 55){
			if(prefix >= 96 && prefix <= 99)
				return new Carrier(Carrier.VIVO);
			else if(prefix >= 91 && prefix <= 94)
				return new Carrier(Carrier.CLARO); 
			else if(prefix >= 81 && prefix <= 82)
				return new Carrier(Carrier.TIM);
			else if(prefix >= 84 && prefix <= 85)
				return new Carrier(Carrier.OI);
		}else if(ddd >= 31 && ddd <= 38){
			if(prefix >= 96 && prefix <= 99)
				return new Carrier(Carrier.VIVO);
			else if(prefix >= 82 && prefix <= 84)
				return new Carrier(Carrier.CLARO); 
			else if(prefix >= 91 && prefix <= 94)
				return new Carrier(Carrier.TIM);
			else if(prefix >= 86 && prefix <= 88)
				return new Carrier(Carrier.OI);
		}else if(ddd >= 71 && ddd <= 79){
			if(prefix >= 96 && prefix <= 99)
				return new Carrier(Carrier.VIVO);
			else if(prefix >= 82 && prefix <= 84)
				return new Carrier(Carrier.CLARO); 
			else if(prefix >= 91 && prefix <= 94)
				return new Carrier(Carrier.TIM);
			else if(prefix >= 86 && prefix <= 88)
				return new Carrier(Carrier.OI);
		}else if(ddd >= 81 && ddd <= 89){
			if(prefix >= 82 && prefix <= 84)
				return new Carrier(Carrier.VIVO);
			else if(prefix >= 91 && prefix <= 94)
				return new Carrier(Carrier.CLARO); 
			else if(prefix >= 96 && prefix <= 99)
				return new Carrier(Carrier.TIM);
			else if(prefix >= 86 && prefix <= 88)
				return new Carrier(Carrier.OI);
		}else if(ddd >= 61 && ddd <= 69){
			if(prefix >= 96 && prefix <= 99)
				return new Carrier(Carrier.VIVO);
			else if(prefix >= 91 && prefix <= 95)
				return new Carrier(Carrier.CLARO); 
			else if(prefix >= 81 && prefix <= 83)
				return new Carrier(Carrier.TIM);
			else if(prefix >= 84 && prefix <= 86)
				return new Carrier(Carrier.OI);
		}else if(ddd >= 91 && ddd <= 99){
			if(prefix >= 91 && prefix <= 94)
				return new Carrier(Carrier.VIVO);
			else if(prefix >= 91 && prefix <= 94)
				return new Carrier(Carrier.CLARO); 
			else if(prefix >= 80 && prefix <= 83)
				return new Carrier(Carrier.TIM);
			else if((prefix >= 96 && prefix <= 99) || (prefix >= 86 && prefix <= 88))
				return new Carrier(Carrier.OI);
		}
		
		if(number.startsWith("3") || number.startsWith("2"))
			return new Carrier(Carrier.FIXO);

		//Log.d("CarrierFactory", ddd + " " + number);

		return null;
	}
}
