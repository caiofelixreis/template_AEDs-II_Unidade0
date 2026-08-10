import java.text.NumberFormat;
import java.time.LocalDate;

public class ProdutoPerecivel extends Produto {
	private static final double DESCONTO = 0.25;
	private static final int PRAZO_DESCONTO = 7;
	private LocalDate dataValidade;	

	public ProdutoPerecivel(
		String desc,
		double precoCusto,
		double margemLucro,
		LocalDate dataValidade
	) {
		super(desc, precoCusto, margemLucro);
		init(desc, precoCusto, margemLucro, dataValidade);
	}

	private void init(
		String desc, 
		double precoCusto, 
		double margemLucro,
		LocalDate dataValidade
	) {
		if (dataValidade.isAfter(LocalDate.now())) {
			this.dataValidade = dataValidade;
		} else {
			throw new IllegalArgumentException("Data de validade inválida.");
		}
	}

	@Override
	public double valorDeVenda() {
		if (dataValidade.isBefore(LocalDate.now().plusDays(PRAZO_DESCONTO))) {
			return (precoCusto * (1.0 + margemLucro) * (1.0 - DESCONTO));
		} else {
			return (precoCusto * (1.0 + margemLucro));
		}
	}

	@Override
	public String toString() {
		return super.toString() + " - " + dataValidade;
	}

}